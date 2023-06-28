package com.example.martyrs.feature.Martyr.comment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.martyrs.R
import com.example.martyrs.common.EXTRA_KEY_ID
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class AddCommentFragment : DialogFragment() {

    val viewModel: AddCommentViewModel by viewModel()
    val compositeDisposable = CompositeDisposable()
    internal lateinit var listener: NoticeDialogListener

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    interface NoticeDialogListener {
        fun onDialogDismiss()
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as NoticeDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException(
                (context.toString() +
                        " must implement NoticeDialogListener")
            )
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val martyrId: Int? = arguments?.getInt(EXTRA_KEY_ID)

        return activity?.let { it ->
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;
            val v: View = inflater.inflate(R.layout.dialog_add_comment, null)
            // TODO: bake here (add permission)
            val firstName = v.findViewById<TextInputEditText>(R.id.firstNameEt)
            val lastName = v.findViewById<TextInputEditText>(R.id.lastNameEt)
            val phoneNumber = v.findViewById<TextInputEditText>(R.id.phoneNumberEt)
            val comment = v.findViewById<TextInputEditText>(R.id.commentEt)
            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(v)
                // Add action buttons
                .setPositiveButton(R.string.addComment,
                    DialogInterface.OnClickListener { dialog, id ->
                        viewModel.addComment(
                            comment!!.text.toString(),
                            firstName!!.text.toString(),
                            lastName!!.text.toString(),
                            phoneNumber!!.text.toString(),
                            martyrId!!
                        )
                        // TODO: back here (sleep 1000)
                        Thread.sleep(1000)
                        listener.onDialogDismiss()
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()!!.cancel()
                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}