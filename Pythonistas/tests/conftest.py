import shutil
import pytest


@pytest.yield_fixture(autouse=True, scope='session')
def test_suite_cleanup():
    # setup

    yield

    # teardown
    try:
        # Only working on succesfull test while dir gets generated on failed
        shutil.rmtree('.pytest_cache')
    except OSError:
        pass
