package hva.nl.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.quest_layout.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        createItemTouchHelper().attachToRecyclerView(rvQuestion)
        rvQuestion.layoutManager =
            StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        rvQuestion.adapter = questionAdapter
        rvQuestion.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        for (i in Question.QUESTIONS.indices) {
            questions.add(Question(Question.QUESTIONS[i], Question.ANSWERS[i]))
        }
        questionAdapter.notifyDataSetChanged()
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT + ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                questionAdapter.notifyItemChanged(viewHolder.adapterPosition)
                val question = questions[viewHolder.adapterPosition]
                if (question.answer && direction == 8 || !question.answer && direction == 4) {
                    Snackbar.make(rvQuestion, "Correcto", Snackbar.LENGTH_SHORT).show()
                    return
                }

                Snackbar.make(rvQuestion, "FALSE", Snackbar.LENGTH_SHORT).show()


            }
        }
        return ItemTouchHelper(callback)
    }


}
