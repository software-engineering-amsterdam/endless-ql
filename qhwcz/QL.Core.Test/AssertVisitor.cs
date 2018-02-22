﻿using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL.Core.Ast;
using QL.Core.Ast.Visitors;
using System;
using System.Collections.Generic;

namespace QL.Core.Test
{
    public sealed class AssertVisitor : IVisitor
    {
        private Queue<Action<FormNode>> _formNodeExpectations = new Queue<Action<FormNode>>();
        private Queue<Action<QuestionNode>> _questionNodeExpectations = new Queue<Action<QuestionNode>>();        

        public void EnqueueFormNodeCallback(Action<FormNode> assertionAction)
        {
            _formNodeExpectations.Enqueue(assertionAction);
        }

        public void EnqueueQuestionNodeCallback(Action<QuestionNode> assertionAction)
        {
            _questionNodeExpectations.Enqueue(assertionAction);
        }

        public void VerifyAll()
        {
            Assert.AreEqual(0, _formNodeExpectations.Count);
            Assert.AreEqual(0, _questionNodeExpectations.Count);
        }

        public void Visit(EmptyNode node)
        {
            // No action required
        }

        public void Visit(FormNode node)
        {
            if (_formNodeExpectations.Count > 0)
            {
                _formNodeExpectations.Dequeue().Invoke(node);
            }
        }
  
        public void Visit(QuestionNode node)
        {
            if (_questionNodeExpectations.Count > 0)
            {
                _questionNodeExpectations.Dequeue().Invoke(node);
            }
        }
    }
}
