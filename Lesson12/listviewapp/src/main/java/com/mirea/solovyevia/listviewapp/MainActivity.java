package com.mirea.solovyevia.listviewapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        List<String> titles = generateRealBookTitles(30);
        ListView listView = findViewById(R.id.content_list);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, titles);
        listView.setAdapter(arrayAdapter);
    }

    private static List<String> generateRealBookTitles(int numTitles) {
        List<String> titles = new ArrayList<>();
        String[] realTitles = {
                "To Kill a Mockingbird", "Pride and Prejudice", "1984",
                "The Lord of the Rings", "The Great Gatsby", "The Catcher in the Rye",
                "The Hobbit", "One Hundred Years of Solitude", "Brave New World",
                "Animal Farm", "The Little Prince", "The Alchemist",
                "The Da Vinci Code", "The Hitchhiker's Guide to the Galaxy",
                "Harry Potter and the Sorcerer's Stone", "And Then There Were None",
                "The Chronicles of Narnia", "Gone with the Wind", "The Odyssey",
                "The Divine Comedy", "Moby Dick", "War and Peace", "Crime and Punishment",
                "Jane Eyre", "The Adventures of Huckleberry Finn", "Alice's Adventures in Wonderland",
                "The Count of Monte Cristo", "The Picture of Dorian Gray", "Les Misérables",
                "The Secret Garden"
        };

        Collections.shuffle(Arrays.asList(realTitles));
        titles.addAll(Arrays.asList(realTitles).subList(0, Math.min(numTitles, realTitles.length)));

        return titles;
    }
}