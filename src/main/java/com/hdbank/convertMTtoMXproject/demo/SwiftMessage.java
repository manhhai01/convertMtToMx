package com.hdbank.convertMTtoMXproject.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SwiftMessage {
    ArrayList<String> block1 = new ArrayList<>();
    ArrayList<String> block2 = new ArrayList<>();
    ArrayList<TagBlock3> block3 = new ArrayList<>();
    ArrayList<TagBlock4> block4 = new ArrayList<>();
    ArrayList<TagBlock5> block5 = new ArrayList<>();
}
