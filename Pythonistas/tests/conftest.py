import shutil
import pytest
import os


@pytest.yield_fixture(autouse=True, scope='session')
def test_suite_cleanup():
    # setup

    # run tests
    yield

    # teardown
    rm_file('tests/output_frame_test.txt')
    rm_dir('.pytest_cache')


def rm_dir(location):
    """Removes directory after tests"""
    try:
        shutil.rmtree(location)
    except OSError as e:
        print(e)


def rm_file(location):
    """Removes file after tests"""
    try:
        os.remove(location)
    except OSError as e:
        print(e)
