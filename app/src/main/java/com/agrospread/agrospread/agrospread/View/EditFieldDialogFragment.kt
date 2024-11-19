package com.agrospread.agrospread.agrospread.View

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class EditFieldDialogFragment : DialogFragment() {

    class EditFieldDialogFragment : DialogFragment() {

        interface EditFieldDialogListener {
            fun onDialogPositiveClick(fieldName: String, newValue: String)
        }

        private var listener: EditFieldDialogListener? = null

        companion object {
            private const val ARG_FIELD_NAME = "fieldName"
            private const val ARG_CURRENT_VALUE = "currentValue"

            fun newInstance(fieldName: String, currentValue: String): EditFieldDialogFragment {
                val fragment = EditFieldDialogFragment()
                val args = Bundle()
                args.putString(ARG_FIELD_NAME, fieldName)
                args.putString(ARG_CURRENT_VALUE, currentValue)
                fragment.arguments = args
                return fragment
            }
        }

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val fieldName = arguments?.getString(ARG_FIELD_NAME) ?: ""
            val currentValue = arguments?.getString(ARG_CURRENT_VALUE) ?: ""

            val builder = AlertDialog.Builder(requireActivity())
            builder.setTitle("Edit $fieldName")

            val input = EditText(requireContext())
            input.setText(currentValue)
            builder.setView(input)

            builder.setPositiveButton("OK") { dialog, _ ->
                listener?.onDialogPositiveClick(fieldName, input.text.toString())
                dialog.dismiss()
            }

            builder.setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }

            return builder.create()
        }

        override fun onAttach(context: android.content.Context) {
            super.onAttach(context)
            try {
                listener = context as EditFieldDialogListener
            } catch (e: ClassCastException) {
                throw ClassCastException("$context must implement EditFieldDialogListener")
            }
        }

        override fun onDetach() {
            super.onDetach()
            listener = null
        }
    }
}
