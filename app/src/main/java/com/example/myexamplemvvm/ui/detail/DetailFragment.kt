package com.example.myexamplemvvm.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.myexamplemvvm.R
import com.example.myexamplemvvm.databinding.FragmentDetailBinding
import com.example.myexamplemvvm.ui.listMain.CharacterListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {

  private  val viewModel:CharacterListViewModel by activityViewModels()
  private lateinit var binding: FragmentDetailBinding


  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
   binding = FragmentDetailBinding.inflate(inflater,container,false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.currentCharacter.observe(viewLifecycleOwner){
        binding.title.text = it.name
      binding.statusDetail.text = it.status
       Glide.with(binding.root.context).load(it.image).into(binding.imageDetail)
    }
  }

}