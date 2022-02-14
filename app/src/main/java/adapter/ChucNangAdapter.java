package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.btl_toeic.R;

import java.util.List;

public class ChucNangAdapter extends ArrayAdapter<String> {
    Activity context;
    int resource;
    List<String> objects;
    public ChucNangAdapter(@NonNull Activity context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View row = layoutInflater.inflate(this.resource,null);

        String tenchucnang = this.objects.get(position);
        TextView txtTenChucNang = row.findViewById(R.id.txtTenChucNang);
        txtTenChucNang.setText(tenchucnang);
        return row;
    }
}
