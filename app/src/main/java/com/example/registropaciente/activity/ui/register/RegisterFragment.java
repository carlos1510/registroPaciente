package com.example.registropaciente.activity.ui.register;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import android.net.Uri;
//import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.registropaciente.R;
import com.example.registropaciente.databinding.FragmentRegisterBinding;

import java.io.File;
import java.io.IOException;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    //String[] items = {"09 de octubre", "San fernando", "Manantay", "Fraternidad", "La Florida", "Bolognesi", "7 de Junio", "Micaela Bastidas", "Las Mercedes", "Nueva Pucallpa"};
    //AutoCompleteTextView autoCompleteTextView;

    //ArrayAdapter<String> adapterItems;

    Button btnCamara;
    ImageView imgView;

    String rutaImagen;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RegisterViewModel registerViewModel =
                new ViewModelProvider(this).get(RegisterViewModel.class);

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //autoCompleteTextView = binding.autoCompleteTxt;
        //adapterItems = new ArrayAdapter<String>(View.getContext(), R.layout.list_item);

        //final TextView textView = binding.textView3;
        //registerViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
        //return inflater.inflate(R.layout.list_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        btnCamara = view.findViewById(R.id.btnCamara);
        imgView = view.findViewById(R.id.imageView);
        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.auto_complete_txt);

        String[] items = {"09 de octubre", "San fernando", "Manantay", "Fraternidad", "La Florida", "Bolognesi", "7 de Junio", "Micaela Bastidas", "Las Mercedes", "Nueva Pucallpa"};

        // Crea un adaptador para el AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, items);

        // Configura el adaptador para el AutoCompleteTextView
        autoCompleteTextView.setAdapter(adapter);

        //autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText()
            }
        });

        btnCamara.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                abrirCamara();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private  void abrirCamara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //if (intent.resolveActivity(getPackageManager()) != null){

        File imagenArchivo = null;

        try {
            imagenArchivo = crearImagen();
        }catch (IOException ex){
            Log.e("Error", ex.toString());
        }

        if (imagenArchivo != null){
            Uri fotoUri = FileProvider.getUriForFile(requireContext(), "com.example.registropaciente.fileprovider", imagenArchivo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
            startActivityForResult(intent, 1);
        }
    }

   /* private ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        Bundle extras = data.getExtras();
                        Bitmap imageBitmap = (Bitmap) extras.get("data");
                        // Guarda la imagen en la memoria externa
                        //String imagePath = saveImageToExternalStorage(imageBitmap);
                        // Muestra la imagen en el ImageView
                        imgView.setImageBitmap(imageBitmap);
                    }
                }
            });*/

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            // Guardar la imagen en la memoria externa
            String imagePath = saveImageToExternalStorage(imageBitmap);
            // Mostrar la imagen en el ImageView
            imageView.setImageBitmap(imageBitmap);
        }

        if (requestCode == 1 && resultCode == -1) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            //Bitmap imgBitmap = BitmapFactory.decodeFile(rutaImagen);
            imgView.setImageBitmap(imgBitmap);
        }
    }*/

    private File crearImagen() throws IOException {
        String nombreImagen = "foto_";

        File directorio = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(nombreImagen, ".jpg", directorio);

        rutaImagen = imagen.getAbsolutePath();
        return imagen;
    }
}