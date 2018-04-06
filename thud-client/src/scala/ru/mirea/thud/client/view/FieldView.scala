package ru.mirea.thud.client.view


import java.io.File
import java.net.URL
import java.util.ResourceBundle
import javafx.event.{Event, EventHandler}
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.scene.shape.Rectangle
import javafx.scene.{control => jfxsc}
import javafx.stage.WindowEvent
import javafx.{event => jfxe}
import javafx.{fxml => jfxf}

import scalafx.stage.Stage
import javafx.{scene => jfxs}

import ru.mirea.thud.client.app.ThudGame
import ru.mirea.thud.client.constants.FieldCellType
import ru.mirea.thud.client.mapping.UnitToCellMapping
import ru.mirea.thud.client.model.{FieldUnit, Location}
import ru.mirea.thud.client.model.messages.CalculateMovementSchemeMessage
import ru.mirea.thud.client.state.{GameScore, GameState}
import ru.mirea.thud.common.constants.PlayerRole

import scalafx.Includes._
import scalafx.scene.Scene

class FieldView extends jfxf.Initializable {
  @jfxf.FXML private var suggestToFinish: jfxsc.Button = _
  @jfxf.FXML private var helpButton: jfxsc.Button = _
  var curr_loc: Location=_

  /*Данные игроков*/
  @jfxf.FXML private var firstPlayerName: jfxsc.Label = _
  @jfxf.FXML private var secondPlayerName: jfxsc.Label = _
  @jfxf.FXML private var firstPlayerFirstRole: jfxsc.Label = _
  @jfxf.FXML private var firstPlayerSecondRole: jfxsc.Label = _
  @jfxf.FXML private var firstPlayerFirstScore: jfxsc.Label = _
  @jfxf.FXML private var firstPlayerSecondScore: jfxsc.Label = _
  @jfxf.FXML private var firstPlayerTotalScore: jfxsc.Label = _
  @jfxf.FXML private var secondPlayerFirstRole: jfxsc.Label = _
  @jfxf.FXML private var secondPlayerSecondRole: jfxsc.Label = _
  @jfxf.FXML private var secondPlayerFirstScore: jfxsc.Label = _
  @jfxf.FXML private var secondPlayerSecondScore: jfxsc.Label = _
  @jfxf.FXML private var secondPlayerTotalScore: jfxsc.Label = _

  @jfxf.FXML private var fname: jfxsc.Label = _
  @jfxf.FXML private var sname: jfxsc.Label = _
  @jfxf.FXML private var fvictory: jfxsc.Label = _
  @jfxf.FXML private var svictory: jfxsc.Label = _


  /*Описание поля*/
  /*1 ряд*/
  @jfxf.FXML private var sq_1_6: Rectangle=_
  @jfxf.FXML private var sq_1_7: Rectangle=_
  @jfxf.FXML private var sq_1_8: Rectangle=_
  @jfxf.FXML private var sq_1_9: Rectangle=_
  @jfxf.FXML private var sq_1_10: Rectangle=_

  /*2 ряд*/
  @jfxf.FXML private var sq_2_5: Rectangle=_
  @jfxf.FXML private var sq_2_6: Rectangle=_
  @jfxf.FXML private var sq_2_7: Rectangle=_
  @jfxf.FXML private var sq_2_8: Rectangle=_
  @jfxf.FXML private var sq_2_9: Rectangle=_
  @jfxf.FXML private var sq_2_10: Rectangle=_
  @jfxf.FXML private var sq_2_11: Rectangle=_

  /*3 ряд*/
  @jfxf.FXML private var sq_3_4: Rectangle=_
  @jfxf.FXML private var sq_3_5: Rectangle=_
  @jfxf.FXML private var sq_3_6: Rectangle=_
  @jfxf.FXML private var sq_3_7: Rectangle=_
  @jfxf.FXML private var sq_3_8: Rectangle=_
  @jfxf.FXML private var sq_3_9: Rectangle=_
  @jfxf.FXML private var sq_3_10: Rectangle=_
  @jfxf.FXML private var sq_3_11: Rectangle=_
  @jfxf.FXML private var sq_3_12: Rectangle=_

  /*4 ряд*/
  @jfxf.FXML private var sq_4_3: Rectangle=_
  @jfxf.FXML private var sq_4_4: Rectangle=_
  @jfxf.FXML private var sq_4_5: Rectangle=_
  @jfxf.FXML private var sq_4_6: Rectangle=_
  @jfxf.FXML private var sq_4_7: Rectangle=_
  @jfxf.FXML private var sq_4_8: Rectangle=_
  @jfxf.FXML private var sq_4_9: Rectangle=_
  @jfxf.FXML private var sq_4_10: Rectangle=_
  @jfxf.FXML private var sq_4_11: Rectangle=_
  @jfxf.FXML private var sq_4_12: Rectangle=_
  @jfxf.FXML private var sq_4_13: Rectangle=_

  /*5 ряд*/
  @jfxf.FXML private var sq_5_2: Rectangle=_
  @jfxf.FXML private var sq_5_3: Rectangle=_
  @jfxf.FXML private var sq_5_4: Rectangle=_
  @jfxf.FXML private var sq_5_5: Rectangle=_
  @jfxf.FXML private var sq_5_6: Rectangle=_
  @jfxf.FXML private var sq_5_7: Rectangle=_
  @jfxf.FXML private var sq_5_8: Rectangle=_
  @jfxf.FXML private var sq_5_9: Rectangle=_
  @jfxf.FXML private var sq_5_10: Rectangle=_
  @jfxf.FXML private var sq_5_11: Rectangle=_
  @jfxf.FXML private var sq_5_12: Rectangle=_
  @jfxf.FXML private var sq_5_13: Rectangle=_
  @jfxf.FXML private var sq_5_14: Rectangle=_

  /*6 ряд*/
  @jfxf.FXML private var sq_6_1: Rectangle=_
  @jfxf.FXML private var sq_6_2: Rectangle=_
  @jfxf.FXML private var sq_6_3: Rectangle=_
  @jfxf.FXML private var sq_6_4: Rectangle=_
  @jfxf.FXML private var sq_6_5: Rectangle=_
  @jfxf.FXML private var sq_6_6: Rectangle=_
  @jfxf.FXML private var sq_6_7: Rectangle=_
  @jfxf.FXML private var sq_6_8: Rectangle=_
  @jfxf.FXML private var sq_6_9: Rectangle=_
  @jfxf.FXML private var sq_6_10: Rectangle=_
  @jfxf.FXML private var sq_6_11: Rectangle=_
  @jfxf.FXML private var sq_6_12: Rectangle=_
  @jfxf.FXML private var sq_6_13: Rectangle=_
  @jfxf.FXML private var sq_6_14: Rectangle=_
  @jfxf.FXML private var sq_6_15: Rectangle=_

  /*7 ряд*/
  @jfxf.FXML private var sq_7_1: Rectangle=_
  @jfxf.FXML private var sq_7_2: Rectangle=_
  @jfxf.FXML private var sq_7_3: Rectangle=_
  @jfxf.FXML private var sq_7_4: Rectangle=_
  @jfxf.FXML private var sq_7_5: Rectangle=_
  @jfxf.FXML private var sq_7_6: Rectangle=_
  @jfxf.FXML private var sq_7_7: Rectangle=_
  @jfxf.FXML private var sq_7_8: Rectangle=_
  @jfxf.FXML private var sq_7_9: Rectangle=_
  @jfxf.FXML private var sq_7_10: Rectangle=_
  @jfxf.FXML private var sq_7_11: Rectangle=_
  @jfxf.FXML private var sq_7_12: Rectangle=_
  @jfxf.FXML private var sq_7_13: Rectangle=_
  @jfxf.FXML private var sq_7_14: Rectangle=_
  @jfxf.FXML private var sq_7_15: Rectangle=_

  /*8 ряд*/
  @jfxf.FXML private var sq_8_1: Rectangle=_
  @jfxf.FXML private var sq_8_2: Rectangle=_
  @jfxf.FXML private var sq_8_3: Rectangle=_
  @jfxf.FXML private var sq_8_4: Rectangle=_
  @jfxf.FXML private var sq_8_5: Rectangle=_
  @jfxf.FXML private var sq_8_6: Rectangle=_
  @jfxf.FXML private var sq_8_7: Rectangle=_
  @jfxf.FXML private var sq_8_8: Rectangle=_
  @jfxf.FXML private var sq_8_9: Rectangle=_
  @jfxf.FXML private var sq_8_10: Rectangle=_
  @jfxf.FXML private var sq_8_11: Rectangle=_
  @jfxf.FXML private var sq_8_12: Rectangle=_
  @jfxf.FXML private var sq_8_13: Rectangle=_
  @jfxf.FXML private var sq_8_14: Rectangle=_
  @jfxf.FXML private var sq_8_15: Rectangle=_

  /*9 ряд*/
  @jfxf.FXML private var sq_9_1: Rectangle=_
  @jfxf.FXML private var sq_9_2: Rectangle=_
  @jfxf.FXML private var sq_9_3: Rectangle=_
  @jfxf.FXML private var sq_9_4: Rectangle=_
  @jfxf.FXML private var sq_9_5: Rectangle=_
  @jfxf.FXML private var sq_9_6: Rectangle=_
  @jfxf.FXML private var sq_9_7: Rectangle=_
  @jfxf.FXML private var sq_9_8: Rectangle=_
  @jfxf.FXML private var sq_9_9: Rectangle=_
  @jfxf.FXML private var sq_9_10: Rectangle=_
  @jfxf.FXML private var sq_9_11: Rectangle=_
  @jfxf.FXML private var sq_9_12: Rectangle=_
  @jfxf.FXML private var sq_9_13: Rectangle=_
  @jfxf.FXML private var sq_9_14: Rectangle=_
  @jfxf.FXML private var sq_9_15: Rectangle=_

  /*10 ряд*/
  @jfxf.FXML private var sq_10_1: Rectangle=_
  @jfxf.FXML private var sq_10_2: Rectangle=_
  @jfxf.FXML private var sq_10_3: Rectangle=_
  @jfxf.FXML private var sq_10_4: Rectangle=_
  @jfxf.FXML private var sq_10_5: Rectangle=_
  @jfxf.FXML private var sq_10_6: Rectangle=_
  @jfxf.FXML private var sq_10_7: Rectangle=_
  @jfxf.FXML private var sq_10_8: Rectangle=_
  @jfxf.FXML private var sq_10_9: Rectangle=_
  @jfxf.FXML private var sq_10_10: Rectangle=_
  @jfxf.FXML private var sq_10_11: Rectangle=_
  @jfxf.FXML private var sq_10_12: Rectangle=_
  @jfxf.FXML private var sq_10_13: Rectangle=_
  @jfxf.FXML private var sq_10_14: Rectangle=_
  @jfxf.FXML private var sq_10_15: Rectangle=_

  /*11 ряд*/
  @jfxf.FXML private var sq_11_2: Rectangle=_
  @jfxf.FXML private var sq_11_3: Rectangle=_
  @jfxf.FXML private var sq_11_4: Rectangle=_
  @jfxf.FXML private var sq_11_5: Rectangle=_
  @jfxf.FXML private var sq_11_6: Rectangle=_
  @jfxf.FXML private var sq_11_7: Rectangle=_
  @jfxf.FXML private var sq_11_8: Rectangle=_
  @jfxf.FXML private var sq_11_9: Rectangle=_
  @jfxf.FXML private var sq_11_10: Rectangle=_
  @jfxf.FXML private var sq_11_11: Rectangle=_
  @jfxf.FXML private var sq_11_12: Rectangle=_
  @jfxf.FXML private var sq_11_13: Rectangle=_
  @jfxf.FXML private var sq_11_14: Rectangle=_

  /*12 ряд*/
  @jfxf.FXML private var sq_12_3: Rectangle=_
  @jfxf.FXML private var sq_12_4: Rectangle=_
  @jfxf.FXML private var sq_12_5: Rectangle=_
  @jfxf.FXML private var sq_12_6: Rectangle=_
  @jfxf.FXML private var sq_12_7: Rectangle=_
  @jfxf.FXML private var sq_12_8: Rectangle=_
  @jfxf.FXML private var sq_12_9: Rectangle=_
  @jfxf.FXML private var sq_12_10: Rectangle=_
  @jfxf.FXML private var sq_12_11: Rectangle=_
  @jfxf.FXML private var sq_12_12: Rectangle=_
  @jfxf.FXML private var sq_12_13: Rectangle=_

  /*13 ряд*/
  @jfxf.FXML private var sq_13_4: Rectangle=_
  @jfxf.FXML private var sq_13_5: Rectangle=_
  @jfxf.FXML private var sq_13_6: Rectangle=_
  @jfxf.FXML private var sq_13_7: Rectangle=_
  @jfxf.FXML private var sq_13_8: Rectangle=_
  @jfxf.FXML private var sq_13_9: Rectangle=_
  @jfxf.FXML private var sq_13_10: Rectangle=_
  @jfxf.FXML private var sq_13_11: Rectangle=_
  @jfxf.FXML private var sq_13_12: Rectangle=_

  /*14 ряд*/
  @jfxf.FXML private var sq_14_4: Rectangle=_
  @jfxf.FXML private var sq_14_5: Rectangle=_
  @jfxf.FXML private var sq_14_6: Rectangle=_
  @jfxf.FXML private var sq_14_7: Rectangle=_
  @jfxf.FXML private var sq_14_8: Rectangle=_
  @jfxf.FXML private var sq_14_9: Rectangle=_
  @jfxf.FXML private var sq_14_10: Rectangle=_
  @jfxf.FXML private var sq_14_11: Rectangle=_

  /*15 ряд*/
  @jfxf.FXML private var sq_15_5: Rectangle=_
  @jfxf.FXML private var sq_15_6: Rectangle=_
  @jfxf.FXML private var sq_15_7: Rectangle=_
  @jfxf.FXML private var sq_15_8: Rectangle=_
  @jfxf.FXML private var sq_15_9: Rectangle=_
  @jfxf.FXML private var sq_15_10: Rectangle=_
  @jfxf.FXML private var sq_15_11: Rectangle=_

  @jfxf.FXML private var dr_1:ImageView=_
  @jfxf.FXML private var dr_2:ImageView=_
  @jfxf.FXML private var dr_3:ImageView=_
  @jfxf.FXML private var dr_4:ImageView=_
  @jfxf.FXML private var dr_5:ImageView=_
  @jfxf.FXML private var dr_6:ImageView=_
  @jfxf.FXML private var dr_7:ImageView=_
  @jfxf.FXML private var dr_8:ImageView=_
  @jfxf.FXML private var dr_9:ImageView=_
  @jfxf.FXML private var dr_10:ImageView=_
  @jfxf.FXML private var dr_11:ImageView=_
  @jfxf.FXML private var dr_12:ImageView=_
  @jfxf.FXML private var dr_13:ImageView=_
  @jfxf.FXML private var dr_14:ImageView=_
  @jfxf.FXML private var dr_15:ImageView=_
  @jfxf.FXML private var dr_16:ImageView=_
  @jfxf.FXML private var dr_17:ImageView=_
  @jfxf.FXML private var dr_18:ImageView=_
  @jfxf.FXML private var dr_19:ImageView=_
  @jfxf.FXML private var dr_20:ImageView=_
  @jfxf.FXML private var dr_21:ImageView=_
  @jfxf.FXML private var dr_22:ImageView=_
  @jfxf.FXML private var dr_23:ImageView=_
  @jfxf.FXML private var dr_24:ImageView=_
  @jfxf.FXML private var dr_25:ImageView=_
  @jfxf.FXML private var dr_26:ImageView=_
  @jfxf.FXML private var dr_27:ImageView=_
  @jfxf.FXML private var dr_28:ImageView=_
  @jfxf.FXML private var dr_29:ImageView=_
  @jfxf.FXML private var dr_30:ImageView=_
  @jfxf.FXML private var dr_31:ImageView=_
  @jfxf.FXML private var dr_32:ImageView=_


  @jfxf.FXML private var t_1:ImageView=_
  @jfxf.FXML private var t_2:ImageView=_
  @jfxf.FXML private var t_3:ImageView=_
  @jfxf.FXML private var t_4:ImageView=_
  @jfxf.FXML private var t_5:ImageView=_
  @jfxf.FXML private var t_6:ImageView=_
  @jfxf.FXML private var t_7:ImageView=_
  @jfxf.FXML private var t_8:ImageView=_

  @jfxf.FXML private var sk:ImageView=_



  var dialogStage: Stage = _
//  private val sq1_8:FieldUnit= new FieldUnit("sq_1_8", new Location (sq_1_8.getLayoutX,sq_1_8.getLayoutY), FieldCellType.EMPTY, List())
 // private val sq2_6:FieldUnit= new FieldUnit("sq_2_6", new Location (sq_2_6.getLayoutX,sq_2_6.getLayoutY), FieldCellType.EMPTY, List())

  //var field : Map[String,FieldUnit] = Map()
  //field ++ sq1_8.id -> sq1_8







  @jfxf.FXML private def suggestToExitTheGame(event: jfxe.ActionEvent) {
    val dialog = new SuggestToExitView
    dialog.showDialogSuggestToOffer()
  }


  private def showNotificationDialog(playerName: String, playerScore: String): Unit ={
    View.name=playerName
    View.score=playerScore
    val notif = new NotificationView
    notif.showNotification()
  }

  private def showWinnerDialog(playerScore: String): Unit ={
    WinStage.score=playerScore
    val win = new WinView
    win.showWinnerDialog()
  }

  @jfxf.FXML private def showHelpButton(event: jfxe.ActionEvent) {
    val help = new HelpView
    help.showHelpDialog()
  }

  def colorAttackCells(rectangle: Rectangle):Unit = {
    rectangle.setStyle("-fx-fill:red")
  }

  /*получаем юнита, красим передвижение*/
    /*
  def colorMovementCells(fieldUnit: FieldUnit,rectangle: Rectangle):Unit={
    UnitToCellMapping.getRectangleId(fieldUnit.id)
    rectangle.setStyle("-fx-fill:green")
  }*/

  def colorMovementCells(rectangle: Rectangle):Unit={
    rectangle.setStyle("-fx-fill:green")
  }


  def returnOriginalField(rectangle: Rectangle):Unit={
    rectangle.setStyle("Field.css")
  }

  def deleteFigure(imageView: ImageView):Unit={
    imageView.setVisible(false)
    /*удалить из списка или map*/
  }

  /*параметры получаем от сервера*/
  def setData(firstName:String, firstRole: PlayerRole.Value, firstScore: Int, secondName:String, secondRole: PlayerRole.Value, secondScore: Int):Unit={
    firstPlayerName.setText(firstName)
    firstPlayerFirstRole.setText(firstRole.toString)
    firstPlayerSecondRole.setText(secondRole.toString)
    firstPlayerFirstScore.setText(firstScore.toString)
    firstPlayerSecondScore.setText(secondScore.toString)
    secondPlayerName.setText(secondName)
    secondPlayerFirstRole.setText(secondRole.toString)
    secondPlayerSecondRole.setText(firstRole.toString)
    secondPlayerFirstScore.setText(secondScore.toString)
    secondPlayerSecondScore.setText(firstScore.toString)
  }

  def setTotalScore(firstScore: Int, secondScore: Int): Unit ={
    firstPlayerTotalScore.setText(firstScore.toString)
    secondPlayerTotalScore.setText(secondScore.toString)
  }

  def changeScore(): Unit ={
    //наверное нужна проверка на текущую партию
      firstPlayerFirstScore.setText(GameState.playerState.score.toString)
      firstPlayerSecondScore.setText(GameState.playerState.score.toString)
    //  firstPlayerTotalScore.setText()
      secondPlayerFirstScore.setText(GameState.enemyPlayerState.score.toString)
      secondPlayerSecondScore.setText(GameState.enemyPlayerState.score.toString)
    //  secondPlayerTotalScore.setText()

  }

  def setVictories(): Unit ={
      fname.setText(GameState.playerState.name)
      fvictory.setText(GameState.gameScore.playerVictories.toString)
      sname.setText(GameState.enemyPlayerState.name)
      svictory.setText(GameState.gameScore.enemyPlayerVictories.toString)
  }


  def handleRectangle(rectangle: Rectangle) = {
    colorMovementCells(rectangle)
    val id = rectangle.getId
  //  val unit = GameState.field.units(id)
  //  ThudGame.playerController ! CalculateMovementSchemeMessage(unit)
  }



  def handleImageView(imageView: ImageView) = {
    val id = imageView.getId
    //val unit = GameState.field.units(UnitToCellMapping.getUnitIdByImageView(id))

  }

  @jfxf.FXML private def handleOnMouseClicked(mouseEvent: MouseEvent){
    mouseEvent.getPickResult.getIntersectedNode match {
      case rectangle: Rectangle => handleRectangle(rectangle)
      case imageView: ImageView => handleImageView(imageView)
      case _ =>
    }


    /*не срабатывает*/
    GameView.gameview.dialogStage.setOnCloseRequest(new EventHandler[WindowEvent] {
      override def handle(event: WindowEvent) = {
        val exit = new ExitView
        exit.showExitView()
      }
    })

    //ThudGame.gameController ! CalculateMovementSchemeMessage(unit)
    //System.out.print(mouseEvent.getSource.getClass)
   // colorMovementCells(sq_1_6)
   // System.out.println(mouseEvent.getPickResult.getIntersectedNode.id())
  }

  def showStartField(): Unit = {
    val resourcePath = "C:\\Users\\Анастасия\\Downloads\\Thud-master\\thud-client\\src\\resources"
    val loader: jfxs.Parent = jfxf.FXMLLoader.load(new File(s"$resourcePath\\fxml\\GameScreenViewForm.fxml").toURI.toURL)
    var dialogStage = new Stage(){
      scene = new Scene(loader)
      resizable = false
    }
    GameView.gameview=this
    dialogStage.showAndWait()
  }



  override def initialize(location: URL, resources: ResourceBundle): Unit = {
  }


}

object GameView {
  var gameview: FieldView = _
}
