package hva.nl.swipequiz

data class Question (
    var question: String,
    var answer: Boolean
) {
    companion object {
        val QUESTIONS = arrayOf(
            "A val and Var are the same",
            "MAD grants 12 points",
            "Kotlin and Java are the same thing"
        )
        val ANSWERS = arrayOf(
            false,
            true,
            false

        )
    }

}