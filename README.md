## Вычислитель отличий  

***Hexlet tests and linter status:***  
[![Actions Status](https://github.com/Yakov256/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/Yakov256/java-project-71/actions)
![Java CI](https://github.com/Yakov256/java-project-71/actions/workflows/main.yml/badge.svg)
[![Maintainability](https://api.codeclimate.com/v1/badges/17c5ee080de81c82696a/maintainability)](https://codeclimate.com/github/Yakov256/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/17c5ee080de81c82696a/test_coverage)](https://codeclimate.com/github/Yakov256/java-project-71/test_coverage)

***Описание***  
Вычислитель отличий – программа, определяющая разницу между двумя структурами данных. Это популярная задача, для решения которой существует множество онлайн-сервисов, например: http://www.jsondiff.com/. Подобный механизм используется при выводе тестов или при автоматическом отслеживании изменении в конфигурационных файлах.

***Возможности утилиты***  
Поддержка разных входных форматов: yaml и json    
Генерация отчета в виде plain text, stylish и json  

***Пример использования***  
[Step 5 - Comparison of flat files (JSON)](https://asciinema.org/a/HnPUad2apHyU8xjeMZ5AW4tpB)  
[Step 7 - Comparison of flat files (yaml)](https://asciinema.org/a/srguS7bJtSx4H0WI08vWNI8tM)  
[Step 8 - Comparison files with a recursive structure](https://asciinema.org/a/lL032dksa6STzvS2RgeMcAepD)    
[Step 9 - Plain formatter](https://asciinema.org/a/6ee105GQ7ktolOGFvHXroR717)  
[Step 10 - Json formatter](https://asciinema.org/a/gqaCr9O7AGP4KkmPIUAi1KqUD)  


***Сборка***
| Описание | Команда |
|----------|--------|
| Start    | make |
| Setup    | make build |
| Run      | make run-dist |
| Run tests | make test |
| Run checkstyle | make lint |
| Help  | make help |
