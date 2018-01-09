package chhar.afgaz.com.chhar;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class GameActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        GameAdapter.OnGameClicl onGameClicl = new GameAdapter.OnGameClicl() {
            @Override
            public void onClick() {
                openLoad();
            }
        };
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GameAdapter adapter = new GameAdapter(getImageRes(), getBaseContext(), onGameClicl);
        recyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void openLoad() {
        Intent intent = new Intent(this, SpaceActivity.class);
        startActivity(intent);
    }

    private List<Integer> getImageRes() {

        int[] drawables = {
                R.drawable.book_of_ra_deluxe_1,
                R.drawable.bananas_go_bahamas_1,
                R.drawable.dazzle_me,
                R.drawable.crazy_monkey,
                R.drawable.fairy_land,
                R.drawable.fruit_cocktail,
                R.drawable.fruit_cocktail_2,
                R.drawable.dolphins_pearl,
                R.drawable.gonzos_quest,
                R.drawable.lucky_ladys_charm_1,
                R.drawable.gnome,
                R.drawable.fruit_shop,
                R.drawable.gold_party,
                R.drawable.lost_island,
                R.drawable.lucky_haunter_1,
                R.drawable.keks
        };
        List<Integer> listImg = new ArrayList<>();
        for (int img : drawables) {
            listImg.add(img);
        }
        return listImg;
    }




}
