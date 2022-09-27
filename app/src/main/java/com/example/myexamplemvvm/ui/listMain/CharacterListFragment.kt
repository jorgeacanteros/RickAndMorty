package com.example.myexamplemvvm.ui.listMain

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.example.myexamplemvvm.databinding.FragmentCharacterListBinding
import com.example.myexamplemvvm.domain.model.CharacterModel
import com.example.myexamplemvvm.ui.adapter.AdapterCharacter

class CharacterListFragment : Fragment() {

  private lateinit var binding: FragmentCharacterListBinding

  private  val viewModel:CharacterListViewModel by activityViewModels() //activityViewModels  : CharacterListViewModel
  lateinit var adapterCharacter :AdapterCharacter
  private lateinit var recyclerCharacter : RecyclerView

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentCharacterListBinding.inflate(inflater, container, false)

    return binding.root
  }



  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    recyclerCharacter  = binding.RecyclerCharacterID
     initRecyclerView()
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,ListOnBackPressedCallBack(binding.SlidingPanelLayout))
    viewModel.load()
  }



  private fun initRecyclerView() {

  /*  var listPrueba = listOf <CharacterModel>(
      CharacterModel(name = "nombre1", status = "status1",),
      CharacterModel(name = "nombre2", status = "status2")
    )*/
    adapterCharacter =
     AdapterCharacter(){
           // Toast.makeText(context, it.name,Toast.LENGTH_SHORT).show()
     //this.findNavController().navigate(R.id.detailFragment)
      viewModel.setCurrentCharacter(it)
     binding.SlidingPanelLayout.openPane()
   }
    binding.RecyclerCharacterID.adapter = adapterCharacter
    viewModel.characters.observe(viewLifecycleOwner){
      adapterCharacter.submitList(it)

    }

  }

}

class ListOnBackPressedCallBack(private val sliderPanel:SlidingPaneLayout):OnBackPressedCallback(sliderPanel.isSlideable && sliderPanel.isOpen),SlidingPaneLayout.PanelSlideListener{
  override fun handleOnBackPressed() {
    sliderPanel.closePane()
  }

  override fun onPanelSlide(panel: View, slideOffset: Float) {

  }

  override fun onPanelOpened(panel: View) {
      isEnabled= true
  }

  override fun onPanelClosed(panel: View) {
    isEnabled= false
  }

  init {
    sliderPanel.addPanelSlideListener(this)
  }

}