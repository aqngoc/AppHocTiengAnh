package adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.btl_toeic.R;

import java.util.List;

import Model.ChuDe;

public class ChuDeAdapter extends ArrayAdapter<ChuDe> {
    Activity context;
    int resource;
    List<ChuDe> objects;
    public ChuDeAdapter(@NonNull Activity context, int resource, @NonNull List<ChuDe> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View ro = layoutInflater.inflate(this.resource,null);

        ChuDe chude = this.objects.get(position);
        TextView txtChuDe = ro.findViewById(R.id.txtChuDe);
        txtChuDe.setText(chude.getTenChuDe());
        TextView txtHocDuoc = ro.findViewById(R.id.txtHocDuoc);
        txtHocDuoc.setText(chude.getHocDuoc());
        ImageView imgChuDe = ro.findViewById(R.id.imgChuDe);
        imgChuDe.setImageResource(R.drawable.logotoeic);
        return ro;
    }
}
