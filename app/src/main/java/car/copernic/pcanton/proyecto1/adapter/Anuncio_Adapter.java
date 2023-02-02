package car.copernic.pcanton.proyecto1.adapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.core.Transaction;

import org.jetbrains.annotations.NotNull;

import car.copernic.pcanton.proyecto1.Modelo.Anuncio;
import car.copernic.pcanton.proyecto1.R;
import car.copernic.pcanton.proyecto1.mostrar_anuncio.mostrar_anuncio;

public class Anuncio_Adapter extends FirestoreRecyclerAdapter<Anuncio, Anuncio_Adapter.ViewHolder> {

    public Anuncio_Adapter(@NotNull FirestoreRecyclerOptions<Anuncio> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull Anuncio_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Anuncio model) {
        holder.nombre.setText(model.getNombre());
        holder.precio.setText(model.getPrecio());
        holder.ubicacion.setText(model.getUbicacion() );
        Glide.with(holder.foto.getContext())
                .load(model.getFoto())
                .placeholder(com.firebase.ui.firestore.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.firestore.R.drawable.common_google_signin_btn_icon_disabled)
                .into(holder.foto);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Esto es "+model.getNombre(), Toast.LENGTH_SHORT).show();

                Bundle bundle=new Bundle();
                bundle.putString("nombre", model.getNombre());
                mostrar_anuncio mostrar= new mostrar_anuncio();
                Transaction transaction = new FragmentManager().beginTransaction();
                mostrar.setArguments(bundle);
                transaction.replace(R.id.frame_layout_buscar, mostrar);
                transaction.addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.commit();
            }
        });

    }

    @NonNull
    @Override
    public Anuncio_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_anuncios,parent,false);
        return new Anuncio_Adapter.ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foto;
        TextView nombre;
        TextView precio;
        TextView ubicacion;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foto=itemView.findViewById(R.id.item_image);
            nombre=itemView.findViewById(R.id.textView_nombre);
            precio=itemView.findViewById(R.id.textView_precio);
            ubicacion=itemView.findViewById(R.id.textView_ubicacion);
            cardView=itemView.findViewById(R.id.card_view_anuncios);

        }
    }

}
