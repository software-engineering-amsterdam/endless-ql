import shutil
import pytest



@pytest.yield_fixture(autouse=True, scope='session')
def test_suite_cleanup():
    # setup


    # run tests
    yield

    # teardown
    rm_dir('.pytest_cache')


def rm_dir(location):
    """Removes directory after tests"""
    try:
        shutil.rmtree(location)
    except OSError as e:
        print(e)
