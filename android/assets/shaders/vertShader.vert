attribute vec4 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoord0;

varying vec4 v_color;
varying vec2 v_texCoords;

uniform mat4 u_projTrans;
uniform float waveDataX;
uniform float waveDataY;

void main() {
    v_color = a_color;
    v_texCoords = a_texCoord0;
    
	float x = a_position.x + waveDataY * sin(waveDataX + a_position.x + a_position.y);
	float y = a_position.y + waveDataY * cos(waveDataX + a_position.x + a_position.y);
	
	vec4 newPos = vec4(x, y, a_position.z, a_position.w);
	
    gl_Position = u_projTrans * newPos;
}