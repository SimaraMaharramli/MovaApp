package com.example.movaapp.screen.profil

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movaapp.R
import com.example.movaapp.adapter.CastAdapter
import com.example.movaapp.databinding.FragmentDetailBinding
import com.example.movaapp.databinding.FragmentProfilBinding
import com.example.movaapp.screen.detail.DetailFragmentArgs
import com.example.movaapp.screen.detail.DetailViewModel
import com.example.movaapp.screen.watchList.WatchListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfilFragment : Fragment() {
    private lateinit var binding: FragmentProfilBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView33.setOnClickListener(){
            logout()
            findNavController().navigate(ProfilFragmentDirections.actionProfilFragment2ToLoginFragment())
        }



    }

    private fun logout(){
        val sp = requireContext().getSharedPreferences("local_shared", Context.MODE_PRIVATE)

        sp.edit().putBoolean("isLogin", false).apply()
        }
}