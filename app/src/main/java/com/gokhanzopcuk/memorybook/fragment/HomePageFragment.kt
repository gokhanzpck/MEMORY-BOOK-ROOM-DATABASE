package com.gokhanzopcuk.memorybook.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.Room
import com.gokhanzopcuk.memorybook.Dao.PlaceDao
import com.gokhanzopcuk.memorybook.DataBase.PlaceDataBase
import com.gokhanzopcuk.memorybook.Model.Place
import com.gokhanzopcuk.memorybook.R
import com.gokhanzopcuk.memorybook.adapter.PlaceAdapter
import com.gokhanzopcuk.memorybook.databinding.FragmentHomePageBinding
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePageFragment : Fragment() {
      private val compositeDisposable=CompositeDisposable()
    private lateinit var db:PlaceDataBase
    private lateinit var placeDao: PlaceDao
 private lateinit var binding:FragmentHomePageBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        binding= FragmentHomePageBinding.inflate(inflater, container, false)
        db=Room.databaseBuilder(requireContext(),PlaceDataBase::class.java,"Places").build()
        placeDao=db.placeDao()

        binding.buttonAdd.setOnClickListener {
           Navigation.findNavController(it).navigate(R.id.homeAddGecis)
        }
        compositeDisposable.add(
            placeDao.GetAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )
        return binding.root
    }
    private fun handleResponse(placeList: List<Place>){
        binding.rv.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        val adapter=PlaceAdapter(requireContext(),placeList)
        binding.rv.adapter=adapter
}
}