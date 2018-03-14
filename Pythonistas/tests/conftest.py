import shutil
import pytest
import sys
import os

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)) + '/../')

from tests.debug_grammar import create_temp_files, clean_up_tmp_files


@pytest.yield_fixture(autouse=True, scope='session')
def test_suite_cleanup():
    # setup
    create_temp_files()

    # run tests
    yield

    # teardown
    clean_up_tmp_files()
    rm_dir('.pytest_cache')


def rm_dir(location):
    """Removes directory after tests"""
    try:
        shutil.rmtree(location)
    except OSError as e:
        print(e)
