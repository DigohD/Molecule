attribute vec2 texCoordIn;
attribute vec3 position;

varying vec4 outColor;
varying vec4 texCoordOut;

uniform mat4 transform;

void main(){
	texCoordOut = texCoordIn;
	outColor = vec4(1,1,1,1);
	gl_Position = vec4(position, 1.0) * transform;
}