package com.hfad.materialcalculation;

import android.net.sip.SipSession;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import android.content.Context;
import android.widget.AdapterView;
import android.widget.ListView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class TotaltemsFragment extends ListFragment {

    //создание интерфейса для добавления слушателя к фрагменту
    static interface Listener{
        void itemClicked(long id);
    }
    private Listener listener;

    public TotaltemsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.listener=(Listener)context; //при связывании с активностью, делаем активность слушателем
    }

    //отработка коротокого нажатия
    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id){
        if(listener!=null){
            listener.itemClicked(id);
        }
    }


    @Override
    public void onActivityCreated(Bundle savesState){
        super.onActivityCreated(savesState);

        //удаление элемента по долгому щелчку
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, final long id) {

                //всплывающее уведомление с подвержедением удаления
                Snackbar snackbar = Snackbar.make(getView(),"Подтвердить удаление",Snackbar.LENGTH_LONG);
                snackbar.setAction("УДАЛИТЬ", new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        //удаление записи
                        ((MyApplication)getActivity().getApplication()).deletItem((int)id);
                        //перезагрузка активити
                        getActivity().recreate();
                    }
                });
                snackbar.show();
                return true;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[]items =((MyApplication)getActivity().getApplication()).getTotalItemsArray();
        ArrayAdapter<String> adapterItemsList = new ArrayAdapter<>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                items);
        setListAdapter(adapterItemsList);
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onStart(){
        super.onStart();

    }
}