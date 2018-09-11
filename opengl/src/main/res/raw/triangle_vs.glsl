#version 310 es

in vec2 vPosition;
in vec3 vColor;
uniform mat4 model;
uniform mat4 view;
uniform mat4 project;
out vec4 outColor;

void main() {
    outColor = vec4(vColor, 1);
    gl_Position = project * view * model * vec4(vPosition, 0, 1);
}

