package druyaned.corejava.vol1.ch14.src.actionedthread;

public class ActionedThread {
    
}
/*
There are 3 threads:
    1.  Actor has:
        1.  "run()" with an infinite execution of some business logic.
        2.  Flags: "mustRun", "mustWait".
        2.  "run()" is synchronized by "waitLock".
        5.  Setters of the flags are synchronized by "this".
        -.  "setMustNotWait()" is also synchronized by "waitLock" if "mustWait==true".
    2.  T1 - first director.
    3.  T2 - second director.
Execution:
    1.  Actor is running.
    2.  Sequence of requests:
        1.  T1 -> "setMustWait()".
        2.  T2 -> "setMustWait()".
        3.  T1 -> "setMustNotWait()".
        4.  T2 -> "setMustWait()".
        5.  T1 -> "setMustNotRun()".
Actor.run():
    1.  "while (true)".
    2.  "synchronized (this)".
*/
