package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller {
    @FXML
    Canvas lienzo;
    GraphicsContext context;
    @FXML
    ColorPicker colorPicker;
    Color colorPincel = Color.BLACK;
    @FXML
    Slider slider;
    @FXML
    ComboBox comboOpciones;

    @FXML
    protected void initialize() {
        context = lienzo.getGraphicsContext2D();
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                pintarDibujos(newValue.intValue());
            }
        });
        comboOpciones.getItems().addAll("Cuadricula", "Ajedrez", "Estrella", "Estrella doble","Flor");

        //Traer pixeles para manipularlos
//        context=lienzo.getGraphicsContext2D();
//        context.setFill(Color.BLUE);//Fill relleno a pintar
//        context.fillRect(10,20,100,50);
//        context.strokeRect(100,150,200,100);
//        context.strokeRect(300,250,200,100);
//        context.setFill(Color.GREEN);
//        context.fillOval(250-(50/2),250-(50/2),50,50);
//        context.strokeLine(0,0,lienzo.getWidth()-50,lienzo.getHeight()-50);
        //////////////////////////////////////////////////

    }

    public void pintarDibujos(int valor) {
        context.setFill(Color.WHITESMOKE);                         //para borrar_todo//
        context.fillRect(0, 0, lienzo.getWidth(), lienzo.getHeight());
        context.setFill(colorPincel);
        System.out.println(valor);

        /*Cuadricula*/
        if (comboOpciones.getSelectionModel().getSelectedIndex() == 0) {
            for (double x = 0; x < valor + 1; x++) {
                double divisiones = (double) lienzo.getWidth() / valor;
                context.strokeLine(0, divisiones * x, lienzo.getWidth(), divisiones * x);
                double divisiones2 = (double) lienzo.getHeight() / valor;
                context.strokeLine(divisiones2 * x, 0, divisiones2 * x, lienzo.getHeight());

            }
        }
        /*Ajedrez*/
        else if (comboOpciones.getSelectionModel().getSelectedIndex() == 1) {
            double divisiones = (double) lienzo.getWidth() / valor;
            double divisiones2 = (double) lienzo.getHeight() / valor;
            for (double x = 0; x < valor+1; x++) {
                for (double y = -1; y < valor+1; y++) {
                    if ((x + y + 1) % 2 == 0) {
                        //context.strokeLine(0, divisiones * x, lienzo.getWidth(), divisiones * x);
                        context.fillRect( divisiones * y,divisiones * x, lienzo.getWidth(), divisiones);
                        context.setFill(Color.BLACK);
                    } else {
                        //context.strokeLine(divisiones2 * y, 0, divisiones2 * y, lienzo.getHeight());
                        context.fillRect(divisiones2 * y , divisiones2 * x, divisiones2, lienzo.getHeight());
                        context.setFill(Color.BLUE);
                    }
                }
            }

        }
        /*Estrella*/
        else if (comboOpciones.getSelectionModel().getSelectedIndex() == 2) {
            //Mitades
            double ancho = ((double) lienzo.getWidth()) / 2;
            double alto = ((double) lienzo.getHeight()) / 2;
            //para hacer el gráfico
            context.strokeLine(ancho, 0, ancho, lienzo.getHeight());
            context.strokeLine(0, alto, lienzo.getWidth(), alto);
            double divisiones = ancho / valor;
            double divisiones2 = alto / valor;
            for (double x = 1; x < valor + 1; x++) {
                context.strokeLine(ancho, divisiones * x, ancho + (divisiones * x), ancho);
                context.strokeLine((divisiones * x), ancho, ancho, (ancho + (divisiones * x)));

                context.strokeLine(alto, divisiones2 * x, alto - (divisiones2 * x), alto);
                context.strokeLine((divisiones2 * x) + 250, alto, alto, (alto - (divisiones2 * x)) + 250);

                //context.strokeLine(divisiones2*x,alto,alto,alto-(divisiones2*x));


            }
        }
        /*Estrella doble*/
        else if (comboOpciones.getSelectionModel().getSelectedIndex() == 3) {

        }
        else if (comboOpciones.getSelectionModel().getSelectedIndex() == 4){
            //Mitades
            double ancho = ((double) lienzo.getWidth()) / 2;
            double alto = ((double) lienzo.getHeight()) / 2;
            //para hacer el gráfico
            context.strokeLine(ancho, 0, ancho, lienzo.getHeight());
            context.strokeLine(0, alto, lienzo.getWidth(), alto);
            context.strokeOval(0,0,lienzo.getWidth(),lienzo.getHeight());
            double divisiones = ancho / valor;
            double divisiones2 = alto / valor;

            for (double x = 0; x < valor ; x++) {
                double a =360/valor;
               double r=5*Math.cos(valor);
               double x1=r*Math.cos(valor);
               double y1=r*Math.sin(valor);
                context.strokeOval(ancho,x1+(divisiones2*x),alto,y1+(divisiones2*x));
                context.strokeOval(x1+divisiones2,alto,ancho,y1+divisiones2);

               // context.strokeOval(alto,y1+divisiones2,ancho,x1+divisiones2);
               // context.strokeOval(y1+divisiones2,ancho,alto,x1+divisiones2);
            }

        }
    }

    public void cambiarColor(ActionEvent event) {
        colorPincel = colorPicker.getValue();
    }

    public void borrar(ActionEvent event) {
        // context.setFill(Color.WHITESMOKE);                         //para borrar_todo//
        //context.fillRect(0,0,lienzo.getWidth(),lienzo.getHeight());
        colorPincel = Color.WHITESMOKE;
    }

    public void arrastrar(MouseEvent event) {
        context.setFill(colorPincel);
        context.fillOval(event.getX(), event.getY(), 5, 5);//tamaño del pensal
    }
}
