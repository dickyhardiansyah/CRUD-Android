package me.dicky.crudandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.dicky.crudandroid.R;
import me.dicky.crudandroid.ViewActivity;
import me.dicky.crudandroid.model.Mahasiswa;

/**
 * Created by sulistiyanto on 07/12/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Mahasiswa> mahasiswa;

    public RecyclerViewAdapter(Context context, List<Mahasiswa> mahasiswa) {
        this.context = context;
        this.mahasiswa = mahasiswa;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Mahasiswa mhs = mahasiswa.get(position);
        holder.textViewNim.setText(mhs.getNim());
        holder.textViewNama.setText(mhs.getNama());
        holder.textViewJurusan.setText(mhs.getJurusan());
        holder.textViewJK.setText(mhs.getJk());

    }

    @Override
    public int getItemCount() {
        return mahasiswa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.textNim)TextView textViewNim;
        @BindView(R.id.textNama)TextView textViewNama;
        @BindView(R.id.textJurusan)TextView textViewJurusan;
        @BindView(R.id.textJK)TextView textViewJK;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String nim = textViewNim.getText().toString();
            String nama = textViewNama.getText().toString();
            String jurusan = textViewJurusan.getText().toString();
            String jk = textViewJK.getText().toString();

           /* Intent i = new Intent(context, UpdateActivity.class);
            i.putExtra("nim",nim);
            i.putExtra("nama",nama);
            i.putExtra("jurusan",jurusan);
            i.putExtra("jk",jk);
            context.startActivity(i);
*/
        }
    }
}