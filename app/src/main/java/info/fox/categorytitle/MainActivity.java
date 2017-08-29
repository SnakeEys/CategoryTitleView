package info.fox.categorytitle;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import info.fox.categorytitleview.CategoryTitleView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_content);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        InnerAdapter adapter = new InnerAdapter();
        List<Data> data = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            Data d = new Data();
            if (i == 0) {
                d.endIcon = R.drawable.ic_chevron_right_black_24dp;
                d.startText = "Start Text";
                d.endText = "End Text";
                d.subText = "Sub Text";
                data.add(d);
                continue;
            }
            if (i == 1) {
                d.startText = "Start text with end icon";
                d.endIcon = R.drawable.ic_chevron_right_black_24dp;
                data.add(d);
                continue;
            }
            if (i == 2) {
                d.startText = "Start text";
                d.endText = "End text";
                data.add(d);
                continue;
            }
            if (i == 3) {
                d.startIcon = R.drawable.ic_explore_black_24dp;
                d.endText = "End Text with end icon";
                d.endIcon = R.drawable.ic_chevron_right_black_24dp;
                data.add(d);
            }
//            if (i % 5 == 0) {
//                continue;
//            }
//
//            if (i % 7 == 0) {
//                d.endText = "Only End Text";
//                data.add(d);
//            }
        }
        adapter.data = data;
        rv.setAdapter(adapter);
    }

    private class Data {
        private String startText = "";
        private String endText = "";
        private String subText = "";
        private int startIcon = 0;
        private int endIcon = 0;
    }

    private class InnerAdapter extends RecyclerView.Adapter<InnerHolder> {

        private List<Data> data;


        @Override
        public InnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_title, parent, false);
            return new InnerHolder(view);
        }

        @Override
        public void onBindViewHolder(InnerHolder holder, int position) {
            if (data == null) {
                return;
            }
            Data d = data.get(position);
            holder.view.setStartText(d.startText);
            holder.view.setEndText(d.endText);
            holder.view.setSubText(d.subText);
            if (d.startIcon != 0) {
                holder.view.getStartIcon().setImageDrawable(ContextCompat.getDrawable(MainActivity.this, d.startIcon));
            }
            if (d.endIcon != 0) {
                holder.view.getEndIcon().setImageDrawable(ContextCompat.getDrawable(MainActivity.this, d.endIcon));
            }
        }

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }
    }

    class InnerHolder extends RecyclerView.ViewHolder {
        CategoryTitleView view;
        InnerHolder(View itemView) {
            super(itemView);
            this.view = (CategoryTitleView) itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
