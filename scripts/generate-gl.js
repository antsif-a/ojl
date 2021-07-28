#!/usr/bin/node

const fs = require('fs')
const path = require('path')

const opt = { encoding: 'utf8' }
const source20 = fs.readFileSync(`${__dirname}/../src/main/java/io/summetdev/ojl/graphics/opengl/GL20.java`, opt)
const source30 = fs.readFileSync(`${__dirname}/../src/main/java/io/summetdev/ojl/graphics/opengl/GL30.java`, opt)

const output20 = path.resolve(`${__dirname}/../src/main/java/io/summetdev/ojl/graphics/opengl/impl/GL20Impl.java`)
const output30 = path.resolve(`${__dirname}/../src/main/java/io/summetdev/ojl/graphics/opengl/impl/GL30Impl.java`)

const javaClassTemplate = `
public class %name% {
    %members%
}`

const javaMethodTemplate = `
public %type% %name%(%args%) {
    %code%
}`

fs.writeFileSync(output20, javaClassTemplate.replace("%name%", "GL20Impl"), opt);
