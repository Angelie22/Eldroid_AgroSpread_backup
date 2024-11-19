package com.agrospread.agrospread.agrospread.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.agrospread.agrospread.R

class FeedbackFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_feedback, container, false)

        val ratingBar = view.findViewById<RatingBar>(R.id.rb_rating)
        val feedbackEditText = view.findViewById<EditText>(R.id.et_feedback)
        val submitButton = view.findViewById<Button>(R.id.send_feedback)
        val ratingText = view.findViewById<TextView>(R.id.experience_text)

        val ratingListener = object : RatingBar.OnRatingBarChangeListener {
            override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean) {
                val ratingTextString = when (rating) {
                    5f -> getString(R.string.very_satisfied)
                    4f -> getString(R.string.satisfied)
                    3f -> getString(R.string.okay)
                    2f -> getString(R.string.poor)
                    1f -> getString(R.string.very_poor)
                    else -> ""
                }
                ratingText.text = ratingTextString  // Update the TextView with the corresponding string
            }
        }

        ratingBar.setOnRatingBarChangeListener(ratingListener)

        submitButton.setOnClickListener {
            val rating = ratingBar.rating
            val feedback = feedbackEditText.text.toString().trim()

            if (rating > 0.0f) {
                Toast.makeText(requireActivity(), getString(R.string.feedback_submitted), Toast.LENGTH_SHORT).show()
                ratingBar.rating = 0.0f
                feedbackEditText.text.clear()
            } else {
                Toast.makeText(requireActivity(), getString(R.string.rate_before_submit), Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
