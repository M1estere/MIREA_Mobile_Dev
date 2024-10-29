package com.mirea.solovyevia.recyclerviewapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.content_view);
        List<HistoricEvent> historicEvents = getEventsList();
        recyclerView.setAdapter(new EventRecyclerAdapter(historicEvents));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private List<HistoricEvent> getEventsList() {
        List<HistoricEvent> historicEvents = new ArrayList<>();

        HistoricEvent fallOfTheRomans = new HistoricEvent("Fall of the Roman Empire",
                "The Western Roman Empire officially fell in 476 CE, marking a significant turning point in European history.",
                "fall_of_the_roman_empire");
        HistoricEvent blackDeath = new HistoricEvent("Black Death",
                "A devastating pandemic that swept across Europe and Asia in the mid-14th century, killing millions.",
                "black_death");
        HistoricEvent renaissance = new HistoricEvent("Renaissance",
                "A period of cultural and intellectual rebirth in Europe, beginning in the 14th century and lasting until the 17th century.",
                "renaissance");
        HistoricEvent frenchRevolution = new HistoricEvent("French Revolution",
                "A period of social and political upheaval in France from 1789 to 1799, leading to the overthrow of the monarchy.",
                "french_revolution");
        HistoricEvent worldWarI = new HistoricEvent("World War I",
                "A global conflict that lasted from 1914 to 1918, involving the major powers of Europe and beyond.",
                "world_war_i");

        historicEvents.add(fallOfTheRomans);
        historicEvents.add(blackDeath);
        historicEvents.add(renaissance);
        historicEvents.add(frenchRevolution);
        historicEvents.add(worldWarI);

        return historicEvents;
    }
}