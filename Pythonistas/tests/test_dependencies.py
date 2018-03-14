import sys
import os
import subprocess
import pytest
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)) + '/../')


def test_java_dependency():
    try:
        sp = subprocess.Popen(["java", "-version"], stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        sp.communicate()
        assert True
    except FileNotFoundError:
        print('java not found. Install java')
        assert False


def test_javac_dependency():
    try:
        sp = subprocess.Popen(["javac", "-version"], stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        sp.communicate()
        assert True
    except FileNotFoundError:
        print('javac not found. Install javac')
        assert False
