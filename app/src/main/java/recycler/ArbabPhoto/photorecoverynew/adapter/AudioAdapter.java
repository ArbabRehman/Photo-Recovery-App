package recycler.ArbabPhoto.photorecoverynew.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

import recycler.ArbabPhoto.photorecoverynew.model.AudioModel;
import recycler.ArbabPhoto.photorecoverynew.R;
import recycler.ArbabPhoto.photorecoverynew.utills.Utils;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.MyViewHolder> {

    Context context;
    ArrayList<AudioModel> listPhoto;
    BitmapDrawable placeholder;

    Activity aaa;

    public AudioAdapter(Context context, ArrayList<AudioModel> mList, Activity aaa) {
        listPhoto = new ArrayList<>();
        this.context = context;
        this.listPhoto = mList;
        this.aaa = aaa;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        TextView tvSize;
        CheckBox ivChecked;
        CardView albumCardView;

        public MyViewHolder(View view) {
            super(view);
            tvDate = (TextView) view.findViewById(R.id.tvDate);
            tvSize = (TextView) view.findViewById(R.id.tvSize);
            ivChecked = (CheckBox) view.findViewById(R.id.isChecked);
            albumCardView = (CardView) view.findViewById(R.id.album_card);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_audio, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.tvDate.setText("" + new File(listPhoto.get(position).getPathAudio()).getName());
        holder.tvSize.setText(Utils.formatSize(listPhoto.get(position).getSizeAudio()));
        if (listPhoto.get(position).isCheck()) {
            Log.e("getIsCheck", "onClick11:  yes");
            holder.ivChecked.setChecked(true);
        } else {
            Log.e("getIsCheck", "onClick11: no");
            holder.ivChecked.setChecked(false);
        }


        holder.albumCardView.setOnClickListener(view -> {
            if (listPhoto.get(position).isCheck()) {
                holder.ivChecked.setChecked(false);
                listPhoto.get(position).setCheck(false);
            } else {
                holder.ivChecked.setChecked(true);
                listPhoto.get(position).setCheck(true);
            }
        });
        holder.ivChecked.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {

                if (listPhoto.get(position).isCheck()) {
                    holder.ivChecked.setChecked(false);
                    listPhoto.get(position).setCheck(false);
                } else {
                    holder.ivChecked.setChecked(true);
                    listPhoto.get(position).setCheck(true);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return listPhoto.size();
    }

    public ArrayList<AudioModel> getSelectedItem() {
        ArrayList<AudioModel> arrayList = new ArrayList();
        if (this.listPhoto != null) {
            for (int i = 0; i < this.listPhoto.size(); i++) {
                if ((this.listPhoto.get(i)).isCheck()) {
                    arrayList.add(this.listPhoto.get(i));
                }
            }
        }
        return arrayList;
    }

    public void setAllImagesUnseleted() {
        if (this.listPhoto != null) {
            for (int i = 0; i < this.listPhoto.size(); i++) {
                if ((this.listPhoto.get(i)).isCheck()) {
                    (this.listPhoto.get(i)).setCheck(false);
                }
            }
        }
    }
}
