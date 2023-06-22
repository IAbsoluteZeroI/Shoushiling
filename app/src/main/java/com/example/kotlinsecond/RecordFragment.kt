package com.example.kotlinsecond

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.kotlinsecond.databinding.FragmentRecordsBinding
import com.example.kotlinsecond.db.DatabaseSingleton
import com.example.kotlinsecond.db.MyDB


class RecordFragment : Fragment() {

    private var _binding: FragmentRecordsBinding? = null
    private val binding get() = _binding!!
    val db by lazy {
        DatabaseSingleton.getDB(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRecordsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recordsView.text = ""
        val dao = db.getDao()
        dao.getAll().observe(this.viewLifecycleOwner) {
                notes ->
                run {
                    val rec = dao.getAll()
                    var s = ""
                    for (note in notes) {
                        s += note.username +" "+ note.wins.toString()+"\n"
                    }
                    binding.recordsView.text = s

                }
        }


        binding.buttonBeck2.setOnClickListener {
            findNavController().navigate(R.id.action_recordFragment_to_mainFragment)
        }
    }
}