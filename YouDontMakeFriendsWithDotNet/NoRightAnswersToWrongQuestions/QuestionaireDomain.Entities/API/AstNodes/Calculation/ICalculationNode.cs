﻿namespace QuestionaireDomain.Entities.API.AstNodes.Calculation
{
    public interface ICalculationNode : IAstNode
    {
        string CalculationDefinition { get; }
    }
}