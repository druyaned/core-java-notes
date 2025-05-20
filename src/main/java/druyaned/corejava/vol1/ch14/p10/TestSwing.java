package druyaned.corejava.vol1.ch14.p10;

public class TestSwing implements Runnable {
    
    @Override public void run() {
        System.out.println(
        """
        Two constraints to keep in mind:
            1. Time-consuming tasks should not be run on
                the Event Dispatch Thread. Otherwise
                the application becomes unresponsive.
            2. Swing components should be accessed on
                the Event Dispatch Thread only.
        But there are some usefull thread-safe methods:
            - JTextComponent.setText()
            - JTextArea.insert()
            - JTextArea.append()
            - JTextArea.replaceRange()
            - JComponent.repaint()
            - JComponent.revalidate()
        """);
    }
    
}
