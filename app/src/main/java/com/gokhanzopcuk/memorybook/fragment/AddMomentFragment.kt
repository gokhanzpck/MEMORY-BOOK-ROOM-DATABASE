package com.gokhanzopcuk.memorybook.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.gokhanzopcuk.memorybook.Dao.PlaceDao
import com.gokhanzopcuk.memorybook.DataBase.PlaceDataBase
import com.gokhanzopcuk.memorybook.Model.Place
import com.gokhanzopcuk.memorybook.R
import com.gokhanzopcuk.memorybook.databinding.FragmentAddMomentBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AddMomentFragment : Fragment() {
    private lateinit var binding: FragmentAddMomentBinding
    private lateinit var db:PlaceDataBase
    private lateinit var placeDao: PlaceDao
    var compositeDisposable=CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
      binding= FragmentAddMomentBinding.inflate(inflater, container, false)
        db=Room.databaseBuilder(requireContext(),PlaceDataBase::class.java,"Places")
            //.allowMainThreadQueries()
            .build()
        placeDao=db.placeDao()
     var id=0

    binding.button.setOnClickListener {
       val location=Place(id,binding.editTextText.text.toString(),binding.editTextText2.text.toString())
        //compositeDisposable sürekli kendini yenileyen kullanılanları atan cöp kutusu gibi düşünülebilir
     compositeDisposable.add(
         placeDao.insert(location)
             .subscribeOn(Schedulers.io())//arka planda çalıştır
             .observeOn(mainThread())//main threadde gözlemle
             .subscribe()//bitince bunu çalıştır
     )
        Navigation.findNavController(it).navigate(R.id.addHomeGecis)
    }
        return binding.root
    }
}