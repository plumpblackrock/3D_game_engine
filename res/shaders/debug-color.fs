#version 120
#include "sampling.glh"

uniform vec3 R_debug_color;


void main()
{
	gl_FragColor = vec4(R_debug_color, 1);
}
