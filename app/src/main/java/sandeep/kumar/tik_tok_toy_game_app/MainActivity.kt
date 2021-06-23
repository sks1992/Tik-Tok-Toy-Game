package sandeep.kumar.tik_tok_toy_game_app

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var PLAYER = true
    var TURN_COUNT = 0

    //this store the our board status so don't need to travers every time
    var boardStatus = Array(3) { IntArray(3) }

    lateinit var board: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create an array of 3 *3 buttons
        board = arrayOf(
            arrayOf(button1, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )

        for (i in board) {
            for (button in i) {
                button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        button_reset.setOnClickListener {

            PLAYER = true
            TURN_COUNT = 0
            initializeBoardStatus()
            setDisplay("")
        }
    }

    private fun initializeBoardStatus() {

        for (i in 0..2) {
            for (j in 0..2) {
                boardStatus[i][j] = -1

            }
        }

        for ( i in board){
            for (button in i){
                button.isEnabled = true
                button.text = ""
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {

            R.id.button1 -> {
                updateValue(raw=0,cal =0,player=PLAYER)
            }
            R.id.button2 -> {
                updateValue(raw=0,cal =1,player=PLAYER)
            }
            R.id.button3 -> {
                updateValue(raw=0,cal =2,player=PLAYER)
            }
            R.id.button4 -> {
                updateValue(raw=1,cal =0,player=PLAYER)
            }
            R.id.button5 -> {
                updateValue(raw=1,cal =1,player=PLAYER)
            }
            R.id.button6 -> {
                updateValue(raw=1,cal =2,player=PLAYER)
            }
            R.id.button7 -> {
                updateValue(raw=2,cal =0,player=PLAYER)
            }
            R.id.button8 -> {
                updateValue(raw=2,cal =1,player=PLAYER)
            }
            R.id.button9 -> {
                updateValue(raw=2,cal =2,player=PLAYER)
            }
        }

        TURN_COUNT++
        PLAYER =!PLAYER

        if (PLAYER){
            setDisplay("Player X Turn")
        } else {
            setDisplay("Player O Turn")
        }

        if (TURN_COUNT ==9){
            setDisplay("Game Draw")
        }

        checkWinner()
    }

    private fun checkWinner() {
        //horizontal raw
        for ( i in 0..2){
            if (boardStatus[i][0] ==boardStatus[i][1] && boardStatus[i][0] ==boardStatus[i][2]){
                if (boardStatus[i][0] ==0){
                    setDisplay("Winner is O")
                    break
                } else if (boardStatus[i][0] == 1){
                    setDisplay("Winner is X")
                    break
                }
            }
        }

        //Vertical column
        for ( i in 0..2){
            if (boardStatus[0][i] ==boardStatus[1][i] && boardStatus[0][i] ==boardStatus[2][i]){
                if (boardStatus[0][i] ==0){
                    setDisplay("Winner is O")
                    break
                } else if (boardStatus[0][i] == 1){
                    setDisplay("Winner is X")
                    break
                }
            }
        }

        //first diagonal
        if (boardStatus[0][0] ==boardStatus[1][1] && boardStatus[0][0] ==boardStatus[2][2]){
            if (boardStatus[0][0] ==0){
                setDisplay("Winner is O")
            } else if (boardStatus[0][0] == 1){
                setDisplay("Winner is X")
            }
        }

        //Second diagonal
        if (boardStatus[0][2] ==boardStatus[1][1] && boardStatus[0][2] ==boardStatus[2][0]){
            if (boardStatus[0][2] ==0){
                setDisplay("Winner is O")
            } else if (boardStatus[0][2] == 1){
                setDisplay("Winner is X")
            }
        }
    }


    private fun disableButton(){

        for (i in board){
            for (button in i){
                button.isEnabled =false
            }
        }
    }

    private fun setDisplay(text: String) {
        text_view_player_turn.text =text

        if (text.contains("Winner")){
            disableButton()
        }
    }


    private fun updateValue(raw: Int, cal: Int, player: Boolean) {

        val text = if (player) "X" else "O"
        val value = if (player) 1 else 0

        board[raw][cal].apply {

            isEnabled =false
            setText(text)
        }

        boardStatus[raw][cal] =value

    }
}