#version 310 es

precision mediump float;
in vec4 outColor;
out vec4 FragColor;

void main() {
    FragColor = outColor;
//    gl_FragColor = vec4(1);
}