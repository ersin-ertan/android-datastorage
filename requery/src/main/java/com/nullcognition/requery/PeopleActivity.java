package com.nullcognition.requery;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.nullcognition.requery.databinding.PersonItemBinding;
import com.nullcognition.requery.model.Person;
import com.nullcognition.requery.model.PersonEntity;
import io.requery.Persistable;
import io.requery.android.QueryRecyclerAdapter;
import io.requery.query.Result;
import io.requery.rx.SingleEntityStore;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class PeopleActivity extends AppCompatActivity {

  private SingleEntityStore<Persistable> data;
  private ExecutorService executor;
  private PersonAdapter adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle("People");
    }

    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    data = (PeopleApplication.get(this)).getData();
    executor = Executors.newSingleThreadExecutor();
    adapter = new PersonAdapter();
    adapter.setExecutor(executor);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    data.count(Person.class).get().toSingle().subscribe(new Action1<Integer>() {
      @Override public void call(Integer integer) {
        if (integer == 0) {
          Observable.fromCallable(new CreatePeople(data))
              .flatMap(new Func1<Observable<Iterable<Person>>, Observable<?>>() {
                @Override public Observable<?> call(Observable<Iterable<Person>> o) {
                  return o;
                }
              })
              .observeOn(Schedulers.computation())
              .subscribe(new Action1<Object>() {
                @Override public void call(Object o) {
                  adapter.queryAsync();
                }
              });
        }
      }
    });
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_plus:
        Intent intent = new Intent(this, PersonEditActivity.class);
        startActivity(intent);
        return true;
    }
    return false;
  }

  @Override protected void onResume() {
    adapter.queryAsync();
    super.onResume();
  }

  @Override protected void onDestroy() {
    executor.shutdown();
    adapter.close();
    super.onDestroy();
  }

  private class PersonAdapter
      extends QueryRecyclerAdapter<PersonEntity, BindingHolder<PersonItemBinding>>
      implements View.OnClickListener {

    private final Random random = new Random();
    private final int[] colors = { Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA };

    PersonAdapter() {
      super(PersonEntity.$TYPE);
    }

    @Override public Result<PersonEntity> performQuery() {
      // this is all persons in the db sorted by their name
      // note this method in executed in a background thread.
      // (Alternatively RxJava w/ RxBinding could be used)
      return data.select(PersonEntity.class).orderBy(PersonEntity.NAME.lower()).get();
    }

    @Override
    public void onBindViewHolder(PersonEntity item, BindingHolder<PersonItemBinding> holder,
        int position) {
      holder.binding.setPerson(item);
      holder.binding.picture.setBackgroundColor(colors[random.nextInt(colors.length)]);
    }

    @Override
    public BindingHolder<PersonItemBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      PersonItemBinding binding = PersonItemBinding.inflate(inflater);
      binding.getRoot().setTag(binding);
      binding.getRoot().setOnClickListener(this);
      return new BindingHolder<>(binding);
    }

    @Override public void onClick(View v) {
      PersonItemBinding binding = (PersonItemBinding) v.getTag();
      if (binding != null) {
        Intent intent = new Intent(PeopleActivity.this, PersonEditActivity.class);
        intent.putExtra(PersonEditActivity.EXTRA_PERSON_ID, binding.getPerson().getId());
        startActivity(intent);
      }
    }
  }
}