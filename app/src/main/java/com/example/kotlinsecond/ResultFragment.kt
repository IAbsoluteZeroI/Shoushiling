package com.example.kotlinsecond

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.example.kotlinsecond.databinding.FragmentResultBinding
import com.example.kotlinsecond.db.DatabaseSingleton
import com.example.kotlinsecond.db.MyDB
import com.example.kotlinsecond.db.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    val db by lazy {
        DatabaseSingleton.getDB(requireContext())
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao = db.getDao()
        binding.buttonSave.setOnClickListener {

            val username = binding.editUsername.text.toString()


            GlobalScope.launch{
                val user = dao.getUser(username)
                if(user != null){
                    user.wins++
                    dao.update(user)

                } else{
                    val newUser = User(username = username, wins = 1)
                    dao.insert(newUser)
                }


            }

            findNavController().navigate(R.id.action_resultFragment_to_mainFragment)

        }

    }
}