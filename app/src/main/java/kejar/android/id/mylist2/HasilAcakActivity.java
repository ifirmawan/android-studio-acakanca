package kejar.android.id.mylist2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HasilAcakActivity extends AppCompatActivity {
    FloatingActionButton share_fab;
    ListView lv;
    //Bundle bundle;
    String share_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_acak);

        share_fab           = (FloatingActionButton) findViewById(R.id.share_fab);
        Intent intent       = getIntent();
        String jumlah_team  = intent.getStringExtra("jumlah_team");
        String[] peserta    = intent.getStringArrayExtra("teams");

        if (jumlah_team.length() > 0){

            lv = (ListView) findViewById(R.id.hasil_acak_list_view); // Get reference of widgets from XML layout

            int size        = peserta.length;
            Random rand     = new Random();
            ArrayList<String> list = new ArrayList<>(size);
            for(int i = 0; i < size; i++) {
                list.add(peserta[i]);
            }
            String[] hasilRandom = new String[size];
            int x  = 0;
            int n  = Integer.valueOf(jumlah_team);
            int jumlah_team_min_satu = n -1;
            int angka = 1;
            share_text ="";
            while(list.size() > 0) {
                int index = rand.nextInt(list.size());
                int index_cek =x + jumlah_team_min_satu;
                int hasil_mod = x % n;
                hasilRandom[x] =" ";
                if (hasil_mod == 0 ){
                //if (x % n == 0 && peserta[index_cek] != null){
                    hasilRandom[x] += "Tim "+angka+" : ";//list.remove(index);
                    angka++;
                }
                hasilRandom[x] += list.remove(index);
                share_text += hasilRandom[x]+"\n";
                x++;
            }
            if (hasilRandom.length > 0){
                // Buat daftar dari larik element bertipe String
                final List<String> fruits_list = new ArrayList<String>(Arrays.asList(hasilRandom));
                // Buat sebuah Adapter Larik
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                        (this, android.R.layout.simple_list_item_1, fruits_list);
                // Menambahkan header pada list
                LayoutInflater inflater = getLayoutInflater();
                ViewGroup header = (ViewGroup)inflater.inflate(R.layout.listview_header,lv,false);
                lv.addHeaderView(header);

                // DataBind ListView with items from ArrayAdapter
                lv.setAdapter(arrayAdapter);

            }else{
                Toast.makeText(getBaseContext(),"Peserta tidak boleh kosong",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"Input Jumlah Team Tidak Boleh kosong",Toast.LENGTH_SHORT).show();
        }
        //klik untuk menghubungkan aplikasi berbagi
        share_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, share_text);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));

            }
        });
    }
}
