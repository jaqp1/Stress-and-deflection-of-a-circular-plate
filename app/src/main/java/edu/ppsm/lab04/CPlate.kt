package edu.ppsm.lab04

import kotlin.math.PI
import kotlin.math.ln
import kotlin.math.pow

class CPlate(var f: Double, var r: Double, var h:Double) {
    val Ni = 0.3;
    val E = 2.1E5;

    fun calculateUmax(): Double = f/h.pow(2)*((Ni+1)*(0.485*ln(r/h)+0.52)+0.48)
    fun calculateSigma(): Double = (f*r.pow(2)*(3+Ni))/16* PI*((E*h.pow(3))/12*(1-Ni.pow(2)))*(1+Ni)

}