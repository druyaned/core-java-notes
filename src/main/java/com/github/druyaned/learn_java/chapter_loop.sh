#!/bin/bash
titles=(
    "Introduction"\
    "Programming Environment"\
    "Basic Constructs"\
    "Objects and Classes"\
    "Inheritance"\
    "Interfaces, Lambda and Internal Classes"\
    "Exceptions, Assertions, and Logging"\
    "Generics"\
    "Collections"\
    "Graphics"\
    "Event Handling"\
    "GUI"\
    "App Deploying"\
    "Concurrency"\
);
for i in {1..14}
do
    if (( i < 10 ))
    then
        num="0$i";
    else
        num="$i";
    fi
    f="vol1/chapter${num}/Chapter${num}.java"
    echo "package com.github.druyaned.learn_java.vol1.chapter${num};"; # > $f
    echo ""; # >> $f
    echo "import static com.github.druyaned.ConsoleColors.*;"; # >> $f
    echo "import com.github.druyaned.learn_java.Chapterable;"; # >> $f
    echo ""; # >> $f
    echo "/**"; # >> $f
    echo " * Practice implementation of the chapter ${i}."; # >> $f
    echo " * "; # >> $f
    echo " * @author druyaned"; # >> $f
    echo " * @see com.github.druyaned.learn_java.vol1.Volume1"; # >> $f
    echo " */"; # >> $f
    echo "public class Chapter${num} implements Chapterable {"; # >> $f
    echo ""; # >> $f
    echo "    @Override"; # >> $f
    echo "    public void run() {"; # >> $f
    echo "        System.out.println(bold(\"Running Chapter${num}: ${titles[$i]}\"));"; # >> $f
    echo "    }"; # >> $f
    echo ""; # >> $f
    echo "    @Override"; # >> $f
    echo "    public int getNumber() { return ${i}; }"; # >> $f
    echo "    "; # >> $f
    echo "    @Override"; # >> $f
    echo "    public String getTitle() { return \"${titles[$i]}\"; }"; # >> $f
    echo "    "; # >> $f
    echo "    @Override"; # >> $f
    echo "    public boolean passed() { return false; }"; # >> $f
    echo "}"; # >> $f
#    echo "$f is written"
done
