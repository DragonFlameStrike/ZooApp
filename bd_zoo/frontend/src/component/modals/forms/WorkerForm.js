import React from 'react';
import { Routes ,Route } from 'react-router-dom';
import {Col, Form, Label, Row} from "reactstrap";



const WorkerForm = (props) => {
    return (
        <Form>
            <Row>
                <Col className="col-12">
                    <div className="mb-3">
                        <Label className="form-label">Контактные данные:</Label>
                        {"89132017838"}
                    </div>
                </Col>
            </Row>
        </Form>
    );
}
export default WorkerForm;