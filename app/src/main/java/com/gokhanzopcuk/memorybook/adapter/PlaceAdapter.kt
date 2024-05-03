package com.gokhanzopcuk.memorybook.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.gokhanzopcuk.memorybook.Model.Place
import com.gokhanzopcuk.memorybook.databinding.CardTasarimBinding
import com.gokhanzopcuk.memorybook.databinding.FragmentHomePageBinding
import com.gokhanzopcuk.memorybook.fragment.HomePageFragmentDirections

class PlaceAdapter (var mContext:Context,val placeList: List<Place>):RecyclerView.Adapter<PlaceAdapter.PlaceHolder>() {
   inner class PlaceHolder(var binding:CardTasarimBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
       val recylerRowBinding=CardTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return PlaceHolder(recylerRowBinding)
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    override fun onBindViewHolder(holder: PlaceHolder, position: Int) {

        val ad=placeList.get(position)
        val t=holder.binding
        t.textView4.text=ad.placeName
    t.ct.setOnClickListener {
        val gecis=HomePageFragmentDirections.homeDeleteGecis(gecis2 =ad)
        Navigation.findNavController(it).navigate(gecis)
    }
    }

}