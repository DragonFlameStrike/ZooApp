import axios from 'axios';
import React, { useState, useEffect } from 'react';
import img from '../../photo/animals/default.png';
import leo from '../../photo/animals/Лев.png';
import tiger from '../../photo/animals/Тигр.png';
import whiteBear from '../../photo/animals/БелыйМедведь.png';
import wolf from '../../photo/animals/Волк.png';
import fox from '../../photo/animals/Лиса.png';
import straus from '../../photo/animals/Страус.png';
import parrot from '../../photo/animals/Попугай.png';
import blackBear from '../../photo/animals/БурыйМедведь.png';
import wet from "../../photo/workers/Ветеринар.png";
import cook from "../../photo/workers/Кормильщик.png";
import cleaner from "../../photo/workers/Уборщик.png";
import looker from "../../photo/workers/Надзиратель.png";
import superman from "../../photo/workers/Разнорабочий.png";
import "../../style.css"

class Cage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            animals: [],
            workers: []
        };
        this.images = {
            "Лев": leo,
            "Тигр": tiger,
            "Белый Медведь": whiteBear,
            "Волк": wolf,
            "Лиса": fox,
            "Страус": straus,
            "Попугай": parrot,
            "Бурый Медведь": blackBear,
            "Ветеринар" : wet,
            "Кормильщик" : cook,
            "Уборщик" : cleaner,
            "Надзиратель" : looker,
            "Разнорабочий" : superman,
        }
        this.animationInterval = 20;
        this.moveAnimals = this.moveAnimals.bind(this);
        this.moveWorkers = this.moveWorkers.bind(this);
    }

    componentDidMount() {
        axios.get(`http://localhost:8080/animals/cage/${this.props.index}`)
            .then(response => {
                this.setState({ animals: response.data });
            })
            .catch(error => {
                console.log(error);
            });
        axios.get(`http://localhost:8080/workers/cage/${this.props.index}`)
            .then(response => {
                this.setState({ workers: response.data });
            })
            .catch(error => {
                console.log(error);
            });
        setInterval(this.moveAnimals, this.animationInterval);
        setInterval(this.moveWorkers, this.animationInterval);
    }

    moveWorkers() {
        const container = document.getElementById(`worker-${this.props.index}`);
        const workers = container.children;

        for (let i = 0; i < workers.length; i++) {
            const worker = workers[i];
            let x = parseFloat(worker.getAttribute("data-x")) || 0;
            let y = parseFloat(worker.getAttribute("data-y")) || 0;
            let dirX = parseFloat(worker.getAttribute("data-dir-x"), 10);
            let dirY = parseFloat(worker.getAttribute("data-dir-y"), 10);

            x += dirX;
            y += dirY;

            // ограничиваем координаты картинки внутри контейнера
            const maxX = container.offsetWidth - worker.offsetWidth;
            const maxY = container.offsetHeight - worker.offsetHeight;

            // меняем направление, если животное столкнулось со стеной
            if (x < 0 || x > maxX) {
                dirX = -dirX;
                worker.setAttribute("data-dir-x", dirX);
                x = Math.max(0, Math.min(maxX, x));
            }

            if (y < 0 || y > maxY) {
                dirY = -dirY;
                worker.setAttribute("data-dir-y", dirY);
                y = Math.max(0, Math.min(maxY, y));
            }

            // обновляем атрибуты data-x и data-y для использования при следующем обновлении
            worker.setAttribute("data-x", x);
            worker.setAttribute("data-y", y);

            // применяем новые координаты для отображения картинки
            worker.style.transform = `translate(${x}px, ${y}px)`;
        }
    }

    moveAnimals() {
        const container = document.getElementById(`cage-${this.props.index}`);
        const animals = container.children;

        for (let i = 0; i < animals.length; i++) {
            const animal = animals[i];
            let x = parseFloat(animal.getAttribute("data-x")) || 0;
            let y = parseFloat(animal.getAttribute("data-y")) || 0;
            let dirX = parseFloat(animal.getAttribute("data-dir-x"), 10);
            let dirY = parseFloat(animal.getAttribute("data-dir-y"), 10);

            x += dirX;
            y += dirY;

            // ограничиваем координаты картинки внутри контейнера
            const maxX = container.offsetWidth - animal.offsetWidth;
            const maxY = container.offsetHeight - animal.offsetHeight;

            // меняем направление, если животное столкнулось со стеной
            if (x < 0 || x > maxX) {
                dirX = -dirX;
                animal.setAttribute("data-dir-x", dirX);
                x = Math.max(0, Math.min(maxX, x));
            }

            if (y < 0 || y > maxY) {
                dirY = -dirY;
                animal.setAttribute("data-dir-y", dirY);
                y = Math.max(0, Math.min(maxY, y));
            }

            // обновляем атрибуты data-x и data-y для использования при следующем обновлении
            animal.setAttribute("data-x", x);
            animal.setAttribute("data-y", y);

            // применяем новые координаты для отображения картинки
            animal.style.transform = `translate(${x}px, ${y}px)`;
        }
    }


    render() {
        const {animals} = this.state;
        const {workers} = this.state;

        return (
            <div style={{
                width: "100%",
                height: "100%",
                position: "relative",
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "space-around"
            }}>
                <div id={`worker-${this.props.index}`} style={{
                    width: "90%",
                    height: "25%",
                    position: "relative",
                    overflow: "hidden",
                    border: "1px solid black",
                }}>
                    {workers.map((worker) => (
                        <img
                            key={worker.id}
                            data-dir-x={(Math.random() * 2 - 1).toFixed(2)}
                            data-dir-y={(Math.random() * 2 - 1).toFixed(2)}
                            style={{
                                width: "50px",
                                height: "50px",
                                position: "absolute"
                            }}
                            src={this.images[worker.profession] || img}
                            alt={worker.name}
                        />
                    ))}
                </div>
                <div id={`cage-${this.props.index}`} style={{
                    width: "90%",
                    height: "70%",
                    position: "relative",
                    overflow: "hidden",
                    border: "1px solid black",
                }}>
                    <div
                        style={{
                            position: "absolute",
                            top: "50%",
                            left: "50%",
                            transform: "translate(-50%, -50%)",
                            fontSize: "24px",
                            fontWeight: "bold",
                            color: "rgba(0, 0, 0, 0.5)", // Слегка прозрачный цвет
                            zIndex: "1", // Устанавливаем значение выше, чем у картинок
                        }}
                    >
                        {`Клетка ${this.props.index}`}
                    </div>
                    {animals.map((animal) => (
                        <img
                            key={animal.id}
                            data-dir-x={(Math.random() * 2 - 1).toFixed(2)}
                            data-dir-y={(Math.random() * 2 - 1).toFixed(2)}
                            style={{
                                width: "50px",
                                height: "50px",
                                position: "absolute"
                            }}
                            src={this.images[animal.type] || img}
                            alt={animal.name}
                        />
                    ))}
                </div>
            </div>
        );
    }
}

export default Cage;