package com.example.myexamplemvvm.ui.listMain

import android.app.ActionBar
import android.app.AlertDialog
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.CalendarContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.compose.ui.graphics.Color
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.example.myexamplemvvm.R
import com.example.myexamplemvvm.databinding.FragmentCharacterListBinding
import com.example.myexamplemvvm.domain.model.CharacterModel
import com.example.myexamplemvvm.ui.adapter.AdapterCharacter
import com.example.myexamplemvvm.ui.adapter.CharacterLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import java.util.logging.XMLFormatter

class CharacterListFragment : Fragment() {

  private lateinit var binding: FragmentCharacterListBinding

  private  val viewModel:CharacterListViewModel by activityViewModels()
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
    //viewModel.loadCharacters()


  }

  override fun onResume() {
    binding.SlidingPanelLayout.closePane()
    super.onResume()
  }

  private fun initRecyclerView() {
    adapterCharacter =
      AdapterCharacter() {
        viewModel.setCurrentCharacter(it)
        binding.SlidingPanelLayout.openPane()

      }



    // viewModel.characters.observe(viewLifecycleOwner){
    //  adapterCharacter.sub(it)
    //}
    lifecycleScope.launchWhenCreated {
      viewModel.characters.collectLatest {
        adapterCharacter.submitData(it)
      }

      adapterCharacter.loadStateFlow.collectLatest {
        if(it.refresh is LoadState.Loading ) {   showError() }
        if(it.refresh is LoadState.Error ) {   showError() }
        if(it.refresh is LoadState.NotLoading ) {   showError() }
      }

    }
    binding.RecyclerCharacterID.adapter = adapterCharacter.withLoadStateHeaderAndFooter(
      header = CharacterLoadStateAdapter {this },
      footer = CharacterLoadStateAdapter { this})
  }

  private fun showError() {
        val dialog = AlertDialog.Builder(context)
        val  view = layoutInflater.inflate(R.layout.item_loading_state,null)
        dialog.setView(view).create().show()
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