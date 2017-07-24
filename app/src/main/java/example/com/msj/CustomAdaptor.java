package example.com.msj;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by selin on 7/8/17.
 */

    /**
     * Created by selin on 7/8/17.
     */

    public class CustomAdaptor extends BaseAdapter {

        LayoutInflater inflater;
        ArrayList<Mesaj> mesajListe;

        public CustomAdaptor(Activity activity, ArrayList<Mesaj> mesajListe){
            this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.mesajListe = mesajListe;

        }


        @Override
        public int getCount() {
            return mesajListe.size();
        }

        @Override
        public Object getItem(int position) {
            return mesajListe.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View satir;
            satir = inflater.inflate(R.layout.custom_satir,null);

            TextView tvGonderen = (TextView) satir.findViewById(R.id.textViewGonderen);
            TextView tvMesaj = (TextView) satir.findViewById(R.id.textViewMesaj);
            TextView tvTarih = (TextView) satir.findViewById(R.id.textViewTarih);

            tvGonderen.setText(mesajListe.get(position).getGonderici());
            tvMesaj.setText(mesajListe.get(position).getMesajText());
            tvTarih.setText(mesajListe.get(position).getZaman());


            return satir;
        }
    }
