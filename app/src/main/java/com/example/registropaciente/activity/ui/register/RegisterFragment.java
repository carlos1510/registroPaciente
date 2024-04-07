package com.example.registropaciente.activity.ui.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.registropaciente.R;
import com.example.registropaciente.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    //String[] items = {"09 de octubre", "San fernando", "Manantay", "Fraternidad", "La Florida", "Bolognesi", "7 de Junio", "Micaela Bastidas", "Las Mercedes", "Nueva Pucallpa"};
    //AutoCompleteTextView autoCompleteTextView;

    //ArrayAdapter<String> adapterItems;

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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}