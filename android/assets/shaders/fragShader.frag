#ifdef GL_ES
    precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;

uniform vec3 color;
uniform sampler2D u_texture;

void main() {
	//vec4 texColor = texture2D(u_texture, v_texCoords);
	//vec4 ambientLight = texColor * 0.8;
    gl_FragColor = texture2D(u_texture, v_texCoords) *  vec4(color, 1.0);
}