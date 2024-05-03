package com.gokhanzopcuk.memorybook.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.gokhanzopcuk.memorybook.DataBase.PlaceDataBase
import com.gokhanzopcuk.memorybook.Model.Place
import com.gokhanzopcuk.memorybook.R
import com.gokhanzopcuk.memorybook.databinding.FragmentDeleteBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DeleteFragment : Fragment() {
     var compositeDisposable=CompositeDisposable()
       private lateinit var binding: FragmentDeleteBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
       binding=FragmentDeleteBinding.inflate(inflater, container, false)
       val db= Room.databaseBuilder(requireContext(), PlaceDataBase::class.java,"Places").build()
            //.allowMainThreadQueries()

      val  placeDao=db.placeDao()
        val bundle:DeleteFragmentArgs by navArgs()
        var gelenBilgi=bundle.gecis2
        val id=gelenBilgi.id
      val baslik=  binding.editTextText3.setText(gelenBilgi.placeName)
       val info= binding.editTextText4.setText(gelenBilgi.placeInfo)
        val location = Place(id,baslik.toString(),info.toString())
        binding.button2.setOnClickListener {

            compositeDisposable.add(
                placeDao.delete(location)
                    .subscribeOn(Schedulers.io())//arka planda çalıştır
                    .observeOn(AndroidSchedulers.mainThread())//main threadde gözlemle
                    .subscribe(this::handleResponse)//bitince bunu çalıştır

            )

        }
        binding.button3.setOnClickListener {
            val ad=binding.editTextText3.text.toString()
            val info=binding.editTextText4.text.toString()
            val duzenle=Place(id,ad,info)
            compositeDisposable.add(
                placeDao.update(duzenle)
                    .subscribeOn(Schedulers.io())//arka planda çalıştır
                    .observeOn(AndroidSchedulers.mainThread())//main threadde gözlemle
                    .subscribe(this::handleResponse)//bitince bunu çalıştır

            )




        }
        return binding.root
    }
    fun handleResponse(){
        Navigation.findNavController(requireView()).navigate(R.id.deleteHomeGecis)

    }


}